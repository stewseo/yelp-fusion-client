package io.github.stewseo.lowlevel.restclient;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public final class DeadHostState implements Comparable<DeadHostState> {

    static final long MAX_CONNECTION_TIMEOUT_NANOS = TimeUnit.MINUTES.toNanos(30);

    static final Supplier<Long> DEFAULT_TIME_SUPPLIER = System::nanoTime;
    private static final long MIN_CONNECTION_TIMEOUT_NANOS = TimeUnit.MINUTES.toNanos(1);
    private final int failedAttempts;
    private final long deadUntilNanos;
    private final Supplier<Long> timeSupplier;

    /**
     * Build the initial dead state of a host. Useful when a working host stops functioning
     * and needs to be marked dead after its first failure. In such case the host will be retried after a minute or so.
     *
     * @param timeSupplier a way to supply the current time and allow for unit testing
     */
    DeadHostState(Supplier<Long> timeSupplier) {
        this.failedAttempts = 1;
        this.deadUntilNanos = timeSupplier.get() + MIN_CONNECTION_TIMEOUT_NANOS;
        this.timeSupplier = timeSupplier;
    }

    /**
     * Build the dead state of a host gi
     * ven its previous dead state. Useful when a host has been failing before, hence
     * it already failed for one or more consecutive times. The more failed attempts we register the longer we wait
     * to retry that same host again. Minimum is 1 minute (for a node the only failed once created
     * through {@link #DeadHostState(Supplier)}), maximum is 30 minutes (for a node that failed more than 10 consecutive times)
     *
     * @param previousDeadHostState the previous state of the host which allows us to increase the wait till the next retry attempt
     */
    DeadHostState(DeadHostState previousDeadHostState) {
        long timeoutNanos = (long) Math.min(
                MIN_CONNECTION_TIMEOUT_NANOS * 2 * Math.pow(2, previousDeadHostState.failedAttempts * 0.5 - 1),
                MAX_CONNECTION_TIMEOUT_NANOS
        );
        this.deadUntilNanos = previousDeadHostState.timeSupplier.get() + timeoutNanos;
        this.failedAttempts = previousDeadHostState.failedAttempts + 1;
        this.timeSupplier = previousDeadHostState.timeSupplier;
    }

    /**
     * Indicates whether it's time to retry to failed host or not.
     *
     * @return true if the host should be retried, false otherwise
     */
    boolean shallBeRetried() {
        return timeSupplier.get() - deadUntilNanos > 0;
    }

    /**
     * Returns the timestamp (nanos) till the host is supposed to stay dead without being retried.
     * After that the host should be retried.
     */
    long getDeadUntilNanos() {
        return deadUntilNanos;
    }

    int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public int compareTo(DeadHostState other) {
        if (timeSupplier != other.timeSupplier) {
            throw new IllegalArgumentException(
                    "can't compare DeadHostStates holding different time suppliers as they may " + "be based on different clocks"
            );
        }
        return Long.compare(deadUntilNanos, other.deadUntilNanos);
    }

    @Override
    public String toString() {
        return "DeadHostState{"
                + "failedAttempts="
                + failedAttempts
                + ", deadUntilNanos="
                + deadUntilNanos
                + ", timeSupplier="
                + timeSupplier
                + '}';
    }
}
