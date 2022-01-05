package com.dy.thrift.server;

import com.dy.thrift.service.HelloWorldService;
import com.dy.thrift.service.impl.HelloWorldServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldNonblockingServer {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldNonblockingServer.class);

    public static final int SERVER_PORT = 8090;

    public void startServer() {
        try {
            TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(
                    new HelloWorldServiceImpl());

            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT);
            TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
            tArgs.processor(tprocessor);
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.protocolFactory(new TCompactProtocol.Factory());
            TServer server = new TNonblockingServer(tArgs);
            server.serve();
        } catch (Exception e) {
            LOG.error(e.toString(), e);
        }
    }

    public static void main(String[] args) {
        HelloWorldNonblockingServer server = new HelloWorldNonblockingServer();
        server.startServer();
    }
}
