import java.util.*;

// Interface for services
interface Serviceable {
    String getServiceId();
    String getCarModel();
    Map<String, Object> getServiceStatus();
}

// Base class for common car service attributes
class CarServiceBase {
    protected String serviceId;
    protected String carModel;
    protected String ownerName;
    protected String contactNumber;

    public CarServiceBase(String serviceId, String carModel, String ownerName, String contactNumber) {
        this.serviceId = serviceId;
        this.carModel = carModel;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}

// CarService class that extends CarServiceBase and implements Serviceable
class CarService extends CarServiceBase implements Serviceable {
    private List<ServiceRecord> serviceHistory;

    public CarService(String serviceId, String carModel, String ownerName, String contactNumber) {
        super(serviceId, carModel, ownerName, contactNumber);
        this.serviceHistory = new ArrayList<>();
    }

    @Override
    public Map<String, Object> getServiceStatus() {
        return ServiceTracker.getServiceStatus(this.serviceId);
    }

    public void addServiceRecord(ServiceRecord record) {
        serviceHistory.add(record);
    }

    public List<ServiceRecord> getServiceHistory() {
        return serviceHistory;
    }
}

// ServiceTracker class for handling service tracking logic
class ServiceTracker {
    public static Map<String, Object> getServiceStatus(String serviceId) {
        Map<String, Object> status = new HashMap<>();
        // Mock data for demonstration
        status.put("serviceId", serviceId);
        status.put("carModel", "Toyota Corolla");
        status.put("serviceStatus", "In Progress");
        status.put("estimatedCompletion", "2 hours");
        status.put("serviceType", "Oil Change & Tire Rotation");
        status.put("serviceCost", "$120");

        return status;
    }
}

// ServiceRecord class to store historical records for each service
class ServiceRecord {
    private String date;
    private String serviceType;
    private String mechanicName;
    private double cost;

    public ServiceRecord(String date, String serviceType, String mechanicName, double cost) {
        this.date = date;
        this.serviceType = serviceType;
        this.mechanicName = mechanicName;
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public double getCost() {
        return cost;
    }
}

// Main class for program execution
public class CarServiceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Car Service Schedule System");
        System.out.print("Enter Service ID to Track: ");
        String serviceId = scanner.nextLine();

        CarService carService = new CarService(serviceId, "Toyota Corolla", "John Doe", "555-1234");

        // Adding mock service history for the car
        carService.addServiceRecord(new ServiceRecord("2024-10-01", "Oil Change", "Mechanic A", 75.0));
        carService.addServiceRecord(new ServiceRecord("2024-09-15", "Brake Check", "Mechanic B", 150.0));
        
        Map<String, Object> serviceStatus = carService.getServiceStatus();

        if (serviceStatus != null) {
            System.out.println("\nService ID: " + serviceStatus.get("serviceId"));
            System.out.println("Car Model: " + serviceStatus.get("carModel"));
            System.out.println("Owner Name: " + carService.getOwnerName());
            System.out.println("Contact Number: " + carService.getContactNumber());
            System.out.println("Service Status: " + serviceStatus.get("serviceStatus"));
            System.out.println("Estimated Completion: " + serviceStatus.get("estimatedCompletion"));
            System.out.println("Service Type: " + serviceStatus.get("serviceType"));
            System.out.println("Service Cost: " + serviceStatus.get("serviceCost"));

            // Display service history
            System.out.println("\nService History:");
            for (ServiceRecord record : carService.getServiceHistory()) {
                System.out.printf("%-15s %-20s %-15s $%.2f%n",
                        record.getDate(),
                        record.getServiceType(),
                        record.getMechanicName(),
                        record.getCost());
            }
        } else {
            System.out.println("Error fetching service status. Please try again later.");
        }

        scanner.close();
    }
}
