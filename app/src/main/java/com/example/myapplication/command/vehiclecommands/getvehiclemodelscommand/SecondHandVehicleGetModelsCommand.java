package com.example.myapplication.command.vehiclecommands.getvehiclemodelscommand;

import com.example.myapplication.model.vehicles.SecondHandVehicle;

import java.util.List;

public class SecondHandVehicleGetModelsCommand implements GetVehicleModelCommand {

    SecondHandVehicle vehicle;

    List<String> list;

    public SecondHandVehicleGetModelsCommand() {
        super();

    }

    @Override
    public List<String> execute() {
        return SecondHandVehicle.getInstance().getAllVehicleWithLicensePlate();
    }
}

