package de.re.easymodbus;

//Java: Modbus TCP Client Example

import de.re.easymodbus.modbusclient.*;

public class run {

  private static JavaToMySQL connector;

  public static void main(String[] args)
  {
    ModbusClient modbusClient = new ModbusClient("192.168.1.42",502);
    try
    {
      modbusClient.Connect();
      while (true) {

        int[] ints = modbusClient.ReadHoldingRegisters(0, 3);
        connector = new JavaToMySQL();
        connector.setData(ints);
        connector.getData();
        Thread.sleep(1000);
      }
    }
    catch (Exception e)
    {
    }



  }
}