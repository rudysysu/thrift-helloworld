namespace java com.dy.sample.thrift.service
 
service HelloWorldService {
  string sayHello(1:string username)
}