package io.github.lowlevel.restclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.impl.client.TargetAuthenticationStrategy;
import org.apache.http.protocol.HttpContext;

public final class PersistentCredentialsAuthenticationStrategy extends TargetAuthenticationStrategy {

    private final Log logger = LogFactory.getLog(PersistentCredentialsAuthenticationStrategy.class);

    @Override
    public void authFailed(HttpHost host, AuthScheme authScheme, HttpContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug(
                    "Authentication to "
                            + host
                            + " failed (scheme: "
                            + authScheme.getSchemeName()
                            + "). Preserving credentials for next request"
            );
        }

    }
}