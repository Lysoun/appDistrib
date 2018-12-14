package jaxws.soap.enseirb;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IHelloWorld {
	String sayHello(@WebParam(name = "name") String name);
}
