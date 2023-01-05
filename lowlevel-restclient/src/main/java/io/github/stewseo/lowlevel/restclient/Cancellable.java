package io.github.stewseo.lowlevel.restclient;


import org.apache.http.client.methods.HttpRequestBase;

import java.util.concurrent.CancellationException;

public abstract class Cancellable {

    static final Cancellable NO_OP = new Cancellable() {
        @Override
        public void cancel() {
        }

        @Override
        void runIfNotCancelled(Runnable runnable) {
            throw new UnsupportedOperationException();
        }
    };

    static Cancellable fromRequest(HttpRequestBase httpRequest) {
        return new Cancellable.TestRequestCancelable(httpRequest);
    }

    static CancellationException newCancellationException() {
        return new CancellationException("request was cancelled");
    }

    public abstract void cancel();

    abstract void runIfNotCancelled(Runnable runnable);

    private static class TestRequestCancelable extends Cancellable {

        private final HttpRequestBase httpRequest;

        private TestRequestCancelable(HttpRequestBase httpRequest) {
            this.httpRequest = httpRequest;
        }

        public synchronized void cancel() {
            this.httpRequest.abort();
        }


        synchronized void runIfNotCancelled(Runnable runnable) {
            if (this.httpRequest.isAborted()) {
                throw newCancellationException();
            }
            runnable.run();
        }
    }
}

