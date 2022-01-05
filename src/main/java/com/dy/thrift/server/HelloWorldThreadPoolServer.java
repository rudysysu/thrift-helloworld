package com.dy.thrift.server;

import com.dy.thrift.service.HelloWorldService;
import com.dy.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldThreadPoolServer {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldThreadPoolServer.class);

    public static final int SERVER_PORT = 8090;

    public void startServer() {
        try {
            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                    new HelloWorldServiceImpl());

            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (Exception e) {
            LOG.error(e.toString(), e);
        }
    }

    public static void main(String[] args) {
        HelloWorldThreadPoolServer server = new HelloWorldThreadPoolServer();
        server.startServer();
    }
}
