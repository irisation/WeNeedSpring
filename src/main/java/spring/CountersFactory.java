package spring;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public abstract class CountersFactory
{

	public abstract Counter getCounter();
//		return new Counter();


}
