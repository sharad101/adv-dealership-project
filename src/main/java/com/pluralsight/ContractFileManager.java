package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private static final String SALES_CONTRACT_FILE = "salesContracts.csv";
    private static final String LEASE_CONTRACT_FILE = "leaseContracts.csv";

    public void saveContract(Contract contract) {
        if (contract instanceof SalesContract salesContract) {
            saveSalesContract(salesContract);
        } else if (contract instanceof LeaseContract leaseContract) {
            saveLeaseContract(leaseContract);
        } else {
            System.out.println("Unknown contract type. Cannot save.");
        }
    }

    private void saveSalesContract(SalesContract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SALES_CONTRACT_FILE, true))) {
            String line = String.format("%s|%s|%s|%d|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%b",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    contract.getVehicleSold().getVin(),
                    contract.getVehicleSold().getMake(),
                    contract.getVehicleSold().getModel(),
                    contract.getVehicleSold().getYear(),
                    contract.getVehicleSold().getPrice(),
                    contract.getSalesTaxAmount(),
                    contract.getRecordingFee(),
                    contract.getProcessingFee(),
                    contract.isFinanceOption());

            writer.write(line);
            writer.newLine();
            System.out.println("Sales contract saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving sales contract: " + e.getMessage());
        }
    }

    private void saveLeaseContract(LeaseContract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEASE_CONTRACT_FILE, true))) {
            String line = String.format("%s|%s|%s|%d|%s|%s|%d|%.2f|%.2f|%.2f",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    contract.getVehicleSold().getVin(),
                    contract.getVehicleSold().getMake(),
                    contract.getVehicleSold().getModel(),
                    contract.getVehicleSold().getYear(),
                    contract.getVehicleSold().getPrice(),
                    contract.getExpectedEndingValue(),
                    contract.getLeaseFee());

            writer.write(line);
            writer.newLine();
            System.out.println("Lease contract saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving lease contract: " + e.getMessage());
        }
    }
}
