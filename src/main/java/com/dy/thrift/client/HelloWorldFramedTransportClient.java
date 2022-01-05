package com.dy.thrift.client;

import com.dy.thrift.service.HelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldFramedTransportClient {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldFramedTransportClient.class);

    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8090;
    public static final int TIMEOUT = 30000;

    public void startClient(String userName) {
        try (TTransport transport = new TFramedTransport(new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT))) {
            TProtocol protocol = new TCompactProtocol(transport);
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            transport.open();
            String result = client.sayHello(userName);
            LOG.info("Thrify client result =: {}", result);
        } catch (TException e) {
            LOG.error(e.toString(), e);
        }
    }

    public static void main(String[] args) {
        HelloWorldFramedTransportClient client = new HelloWorldFramedTransportClient();
        client.startClient("china");
    }
}
