package io.github.stewseo.clients.transport;

import io.github.stewseo.clients.transport.TransportInfo;

public abstract class ResponseBase {

    public abstract TransportInfo _transportInfo();

    public abstract void _transportInfo(TransportInfo info);

}