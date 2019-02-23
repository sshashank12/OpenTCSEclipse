package sample;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.opentcs.access.KernelServicePortal;
import org.opentcs.access.rmi.KernelServicePortalBuilder;
import org.opentcs.access.to.order.DestinationCreationTO;
import org.opentcs.access.to.order.TransportOrderCreationTO;
import org.opentcs.components.kernel.Dispatcher;
import org.opentcs.components.kernel.services.DispatcherService;
import org.opentcs.components.kernel.services.PlantModelService;
import org.opentcs.components.kernel.services.TransportOrderService;
import org.opentcs.components.kernel.services.VehicleService;
import org.opentcs.customizations.kernel.KernelInjectionModule;
import org.opentcs.data.order.DriveOrder.Destination;
import org.opentcs.data.order.TransportOrder;
import org.opentcs.drivers.vehicle.management.ProcessModelEvent;

public class Sample {

	public static void main(String[] args) {
		KernelServicePortal servicePortal = new KernelServicePortalBuilder().build();

		servicePortal.login("localhost", 1099);

		PlantModelService plantModelService = servicePortal.getPlantModelService();
		String modelName = plantModelService.getLoadedModelName();
		
		System.out.println(plantModelService.getModelProperties());
		
		System.out.println(modelName);

		List<Object> events = servicePortal.fetchEvents(1000);

		TransportOrderService tos = servicePortal.getTransportOrderService();

		for (int i = 1; i < 20; i++) {
			DispatcherService ds = servicePortal.getDispatcherService();
			List<DestinationCreationTO> destinations = new LinkedList<>();
			destinations.add(new DestinationCreationTO("Goods in south 01", "Load cargo"));
			
			destinations.add(new DestinationCreationTO("Goods in north 02", "Load cargo"));

			TransportOrderCreationTO orderTO = new TransportOrderCreationTO(
					"MyTransportOrderFROMJava-" + UUID.randomUUID(), destinations);


			orderTO = orderTO.withDeadline(Instant.now().plus(1, ChronoUnit.HOURS));
			TransportOrder newOrder = tos.createTransportOrder(orderTO);

			ds.dispatch();
			
			System.out.println(newOrder);
			
		}

	}

}
