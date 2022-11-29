package io.github.lowlevel.restclient;

import org.apache.commons.logging.*;
import org.apache.http.*;
import org.apache.http.auth.*;
import org.apache.http.impl.client.*;
import org.apache.http.protocol.*;

final class PersistentCredentialsAuthenticationStrategy extends TargetAuthenticationStrategy {

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