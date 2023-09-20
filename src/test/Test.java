package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.ConcreteContainer;
import model.ConcreteGarage;
import model.ConcreteProduct;
import model.ConcreteVehicle;
import model.Container;
import model.Garage;
import model.Hub;
import model.Product;
import model.ProductType;
import model.Vehicle;
import model.VehicleType;
import model.WareHouse;

public class Test {
	
	private WareHouse hub;
	private Garage garage;
	private Container c;
	

	private Product p1 = 
			new ConcreteProduct("T-001", "Iphone 13 128GB", ProductType.TECH, 100.00, 800.00);
	private Product p2 = 
			new ConcreteProduct("T-002", "Galaxy s23 128GB", ProductType.TECH, 150.00, 700.00);
	private Product p3 = 
			new ConcreteProduct("F-001", "dark Chocolate bar BOX", ProductType.FOOD, 500.00, 10.00);
	private Product p4 = 
			new ConcreteProduct("F-002", "white Chocolate bar BOX", ProductType.FOOD, 500.00, 10.00);
	private Product p5 = 
			new ConcreteProduct("C-001", "T-shirt black size L", ProductType.CLOTHES, 250.00, 20.00);
	private Product p6 = 
			new ConcreteProduct("C-002", "T-shirt white size M", ProductType.CLOTHES, 200.00, 20.00);
	
	
	private Vehicle v1 = 
			new ConcreteVehicle("V-1", "Drone", VehicleType.AIR);
	private Vehicle v2 = 
			new ConcreteVehicle("V-2", "Car", VehicleType.LAND);
	
	
	
	private void init() {
		
		hub.add(p1);
		hub.add(p2);
		hub.add(p3);
		hub.add(p4);
		hub.add(p5);
		hub.add(p6);
		
		garage.add(v1);
		garage.add(v2);
		
	}
	
	private void resetInit() {
		
		hub.remove("T-001");
		hub.remove("T-002");
		hub.remove("F-001");
		hub.remove("F-002");
		hub.remove("C-001");
		hub.remove("C-002");
		
		garage.remove("V-1");
		garage.remove("V-2");

		
	}
	
	
	@org.junit.Test
	public void testInit() {
		
		System.out.println("\n--------------------------------------------------------");
		System.out.println("TEST INIT");
		System.out.println("--------------------------------------------------------\n\n");

		
		//CREO L'HUB
		hub = Hub.getInstance();
		//VERIFICO CHE L'HUB SIA VUOTO
		assertTrue(hub.getAllProducts().isEmpty());
		
		//CREO IL GARAGE
		garage = ConcreteGarage.getInstance();
		//VERIFICO CHE IL GARAGE SIA VUOTO
		assertTrue(garage.getAllVehicles().isEmpty());
		
		//AGGIUNGO I PRODOTTI ALL'HUB e I VEICOLI AL GARAGE
		init();
		
		//VERIFICO CHE I PRODOTTI SIANO STATI TUTTI AGGIUNTI
		assertEquals(6, hub.getAllProducts().size());
		
		//VERIFICO CHE I VEICOLI SIANO STATI TUTTI INSERITI
		assertEquals(2, garage.getAllVehicles().size());
		
		//SVUOTO L'HUB ed IL GARAGE
		resetInit();
	}
	
	@org.junit.Test
	public void testHubMethods() {
		
		System.out.println("\n--------------------------------------------------------");
		System.out.println("TEST HUB");
		System.out.println("--------------------------------------------------------\n\n");
		
		hub = Hub.getInstance();
		//VERIFICO CHE L'HUB SIA VUOTO
		assertTrue(hub.getAllProducts().isEmpty());
		
		//PROVO A RECUPERARE UN PRODOTTO NON ANCORA INSERITO
		try {
		assertEquals(p1 , hub.getProductById("T-001"));
		System.out.println("PRODOTTO RECUPERATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		//INSERISCO IL PRODOTTO
		hub.add(p1);
		
		
		//PROVO A RECUPERARE LO STESSO PRODOTTO DI PRIMA DOPO AVERLO INSERITO
		try {
		assertEquals(p1 , hub.getProductById("T-001"));
		System.out.println("PRODOTTO RECUPERATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		//PROVO A MODIFICARE IL PRODOTTO INSERITO IN PRECEDENZA METTENDO UN PREZZO NEGATIVO
		try {
		hub.edit(new ConcreteProduct("T-001", "Iphone 13 128GB", ProductType.TECH, 100.00, -800.00));
		System.out.println("PRODOTTO AGGIORNATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		//PROVO A MODIFICARE IL PRODOTTO INSERITO IN PRECEDENZA METTENDO UN PESO NEGATIVO
		try {
		hub.edit(new ConcreteProduct("T-001", "Iphone 13 128GB", ProductType.TECH, -100.00, 800.00));
		System.out.println("PRODOTTO AGGIORNATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		//PROVO A MODIFICARE IL PRODOTTO INSERITO IN PRECEDENZA METTENDO UN PREZZO E PESO VALIDO
		try {
		hub.edit(new ConcreteProduct("T-001", "Iphone 13 128GB", ProductType.TECH, 200.00, 1000.00));
		System.out.println("PRODOTTO AGGIORNATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		
		//RIMUOVO IL PRODOTTO 
		hub.remove("T-001");
		
		//VERIFICO CHE L'HUB SIA VUOTO
		assertEquals(0, hub.getSize());
		
	}
	
	
	@org.junit.Test
	public void testGarageMethods() {
		
		System.out.println("\n--------------------------------------------------------");
		System.out.println("TEST GARAGE");
		System.out.println("--------------------------------------------------------\n\n");
		
		
		garage = ConcreteGarage.getInstance();
		//VERIFICO CHE IL GARAGE SIA VUOTO
		assertTrue(garage.getAllVehicles().isEmpty());
		
		//PROVO A RECUPERARE UN VEICOLO NON ANCORA INSERITO
		try {
		assertEquals(v1 , garage.getVehicleById("V-1"));
		System.out.println("VEICOLO RECUPERATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		//INSERISCO IL VEICOLO
		garage.add(v1);
		
		
		//PROVO A RECUPERARE LO STESSO VEICOLO DI PRIMA DOPO AVERLO INSERITO
		try {
			assertEquals(v1 , garage.getVehicleById("V-1"));
			System.out.println("VEICOLO RECUPERATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		//PROVO A MODIFICARE LA DESCRIZIONE IN UN VEICOLO NON PRESENTE NEL GARAGE
		try {
		garage.edit(new ConcreteVehicle("V-2", "Drone v1", VehicleType.AIR));
		System.out.println("VEICOLO AGGIORNATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		//PROVO A MODIFICARE LA DESCRIZIONE IN UN VEICOLO PRESENTE NEL GARAGE
		try {
			garage.edit(new ConcreteVehicle("V-1", "Drone v1", VehicleType.AIR));
			System.out.println("VEICOLO AGGIORNATO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		
		//RIMUOVO IL VEICOLO 
		garage.remove("V-1");
		
		//VERIFICO CHE IL GARAGE SIA VUOTO
		assertEquals(0, garage.getSize());

		
	}
	
	
	@org.junit.Test
	public void testContainerAndVehicle() {
		
		System.out.println("\n--------------------------------------------------------");
		System.out.println("TEST CONTAINER");
		System.out.println("--------------------------------------------------------\n\n");
		
		
		// CREO UN CONTAINER CONTENENTE PRODOTTI TECH E TRASPORTABILE DA VEICOLI AEREI
		 c = new ConcreteContainer(ProductType.TECH, VehicleType.AIR);
	

		
		//PROVO AD INSERIRE UN PRODOTTO METTENDO UNA QUANTITA' NEGATIVA
		try {
			c.add(p1, -1);
			System.out.println("PRODOTTO AGGIUNTO AL CONTAINER");
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		//PROVO AD INSERIRE UN PRODOTTO DI TIPO FOOD
		try {
			c.add(p3, 1);
			System.out.println("PRODOTTO AGGIUNTO AL CONTAINER");
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		//PROVO AD INSERIRE 20 PRODOTTI ANDANDO A SFORARE CON IL PESO PER I VEICOLI AEREI
		try {
			c.add(p1, 20);
			System.out.println("PRODOTTO AGGIUNTO AL CONTAINER");
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		//PROVO AD INSERIRE 2 PRODOTTI TECH AL CONTAINER
		try {
			c.add(p1, 2);
			System.out.println("PRODOTTO AGGIUNTO AL CONTAINER");
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		//VERIFCO IL PESO DEL CONTAINER DOPO AVER INSERITO I 2 PRODOTI 
		assertEquals(200.00, c.getQuantity());
		
		
		System.out.println("\n--------------------------------------------------------");
		System.out.println("TEST VEHICLE");
		System.out.println("--------------------------------------------------------\n\n");
		
		
		//PROVO AD INSERIRE IL CONTAINER IN UN VEICOLO LAND
		try {
		v2.setContainer(c);
		System.out.println("CONTAINER AGGIUNTO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		//PROVO AD INSERIRE IL CONTAINER IN UN VEICOLO AIR
		try {
		v1.setContainer(c);
		System.out.println("CONTAINER AGGIUNTO");
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		
		
	}
	

	

	
	

}
