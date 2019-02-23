package sample;

import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Provider;

import org.opentcs.components.kernel.services.InternalTransportOrderService;
import org.opentcs.components.kernel.services.InternalVehicleService;
import org.opentcs.strategies.basic.dispatching.DefaultDispatcher;
import org.opentcs.strategies.basic.dispatching.DefaultDispatcherConfiguration;
import org.opentcs.strategies.basic.dispatching.FullDispatchTask;
import org.opentcs.strategies.basic.dispatching.OrderReservationPool;
import org.opentcs.strategies.basic.dispatching.PeriodicVehicleRedispatchingTask;
import org.opentcs.strategies.basic.dispatching.RerouteTask;
import org.opentcs.strategies.basic.dispatching.TransportOrderUtil;
import org.opentcs.util.event.EventSource;

public class CustomDispatcher extends DefaultDispatcher{

	public CustomDispatcher(OrderReservationPool orderReservationPool, TransportOrderUtil transportOrderUtil,
			InternalTransportOrderService transportOrderService, InternalVehicleService vehicleService,
			EventSource eventSource, ScheduledExecutorService kernelExecutor, FullDispatchTask fullDispatchTask,
			Provider<PeriodicVehicleRedispatchingTask> periodicDispatchTaskProvider,
			DefaultDispatcherConfiguration configuration, RerouteTask rerouteTask) {
		super(orderReservationPool, transportOrderUtil, transportOrderService, vehicleService, eventSource, kernelExecutor,
				fullDispatchTask, periodicDispatchTaskProvider, configuration, rerouteTask);
		// TODO Auto-generated constructor stub
		System.out.println(super.getInfo());
	}
	
	

}
