
//select some important beans
import java.awt.Color;
import org.synesthesia.spring.SpringVertex;
tmp = gController.select("BeanName", "DefaultServiceProxyFactory")
tmp.addAll(gController.select("BeanName", "GenericCRUDServiceImplementation"))
tmp.addAll(gController.select("BeanName", "DTOMapperFactory"))
tmp.addAll(gController.select("BeanName", "TradingPartnerAggregateDTOMapper"))
tmp.addAll(gController.select("BeanName", "DTOEntityConverter"))
tmp.addAll(gController.select("BeanName", "GenericDTOMapper"))
tmp.addAll(gController.select("BeanName", ".*Calc"))
tmp.addAll(gController.select("BeanName", ".*Phase"))
tmp.addAll(gController.select("BeanName", ".*\\.load\\..*"))
tmp.addAll(gController.select("BeanName", "CalcActivityTestHelper"))
tmp.addAll(gController.select("BeanName", "CalcRunner"))
selected.addVertices(tmp);
//[com.syncron.bpe.webservice.jpql.JPQLGenericDAO#301db5ec, Scheduler, QueryFunctionRepository, DataownerTreeProducer, DemandHistoryActivity, SCPAuthorization, ScheduledTaskHelper, ConvertAmountFunction, SchedulerFacade, DAOFactory, SynchronizationJobPrototype]

//show in RED the gain we might get if we make them lazy
Set<SpringVertex> gain = gController.getGain(tmp)
System.out.println(gain.size())
controller.addColor(gain, new Color(255,0,0))
vv.repaint()


// color all *DAOs  
import java.awt.Color;
cur = gController.select("BeanName", ".*DAO")
controller.addColor(cur, new Color(0, 255, 100))
vv.repaint()
println cur.size()

// show ancestors of DAOFactory with distances from it
desc = gController.getAncestorsWithDistances(cur);
controller.addColor(desc, new Color(0,255,255),15);
vv.repaint()
println desc.size()


// collapse all selected vertices into one node
import org.synesthesia.spring.SpringVertex;
SpringVertex v = new SpringVertex();
v.setBeanName("CurrentlySelected");

import java.awt.Color;
import org.synesthesia.spring.SpringVertex;
Set<SpringVertex> cur = (HashSet<SpringVertex>) selected.getVertices()
System.out.println(cur)
graph = graph.substituteWith(cur, v)
vv.getGraphLayout().setGraph(graph)
vv.repaint()


// collapse all .*Services to one node called Services
import org.synesthesia.spring.SpringVertex;
SpringVertex v = new SpringVertex();
v.setBeanName("Services");

import java.awt.Color;
app = gController.select("BeanName", ".*Service");
graph = graph.substituteWith(app, v);
vv.getGraphLayout().setGraph(graph)
vv.repaint()