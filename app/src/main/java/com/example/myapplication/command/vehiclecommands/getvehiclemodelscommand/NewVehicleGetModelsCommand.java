package com.example.myapplication.command.vehiclecommands.getvehiclemodelscommand;

import com.example.myapplication.model.vehicles.NewVehicle;

import java.util.List;

public class NewVehicleGetModelsCommand implements GetVehicleModelCommand {

    NewVehicle vehicle;

    List<String> list;

    public NewVehicleGetModelsCommand() {
        super();

    }


    @Override
    public List<String> execute() {


        return NewVehicle.getInstance().getAllVehicleWithChassisNo();

    }
}

