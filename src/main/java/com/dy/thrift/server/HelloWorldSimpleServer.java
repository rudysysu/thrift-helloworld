package com.dy.thrift.server;

import com.dy.thrift.service.HelloWorldService;
import com.dy.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldSimpleServer {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldSimpleServer.class);

    public static final int SERVER_PORT = 8090;

    public void startServer() {
        try {
            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                    new HelloWorldServiceImpl());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (Exception e) {
            LOG.error(e.toString(), e);
        }
    }

    public static void main(String[] args) {
        HelloWorldSimpleServer server = new HelloWorldSimpleServer();
        server.startServer();
    }
}
