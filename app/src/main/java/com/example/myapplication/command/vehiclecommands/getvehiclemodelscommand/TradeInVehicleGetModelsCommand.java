package com.example.myapplication.command.vehiclecommands.getvehiclemodelscommand;

import com.example.myapplication.model.vehicles.TradeInVehicle;

import java.util.List;

public class TradeInVehicleGetModelsCommand implements GetVehicleModelCommand {


    public TradeInVehicleGetModelsCommand() {
        super();

    }


    @Override
    public List<String> execute() {
        return TradeInVehicle.getInstance().getAllVehicleWithLicensePlate();
    }
}
