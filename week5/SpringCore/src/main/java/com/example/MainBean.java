package com.example;

import com.example.services.MotdServiceBean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainBean {
	
	// the old way before Dependency Injection....
	//private MotdServiceBean motdServiceBean = new MotdServiceBean();
	
	// Due to our Application Context, we're able to configure the instantiation of all beans
	private MotdServiceBean motdServiceBean;
	
	// Dependency Injection via CONSTRUCTOR INJECTION
	
	  // this example will be configured to inject
	  // an instance of the com.example.services.MotdServiceBean into this
	  // MainBean using constructor injection.
	public MainBean(MotdServiceBean motdServiceBean) {
		this.motdServiceBean = motdServiceBean;
	}
	
	
	
	public static void main(String[] args) {
		
		
		
	    // create the application context
	    // this example using XML on the classpath so it is best to create a
	    // ClassPathXmlApplicationContext
	    AbstractApplicationContext ac = new ClassPathXmlApplicationContext("application-context.xml");

	    // if execution gets here then the ApplicationContext should be
	    // configured correctly.
	    // Get a ref to MainBean
	    MainBean mainBean = ac.getBean("MainBean", MainBean.class);

	    // we should be able to invoke the getMotd from the ref to motdServiceBean
	    // Spring took care of creating and "wiring" the beans together
	    System.out.println(mainBean.motdServiceBean.getMotd(-1)); // should be message 1 in the list
	                                                                        // thanks to the IOC injecting the primitive value


	    // the end of the program close the Application context
	    ac.close();


	}

}
