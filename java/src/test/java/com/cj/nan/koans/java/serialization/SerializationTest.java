package com.cj.nan.koans.java.serialization;

import com.cj.nan.koans.impl.Address;
import com.cj.nan.koans.impl.Employee;
import org.junit.Assert;
import org.junit.Test;

public class SerializationTest {

    @Test
    public void serializationAndDeserialization() {
        Employee manager = new Employee();
        manager.setName("Kilian Murphy");
        Address managerAddress = new Address("23 green way", "Baltimore", "New York", 93483);
        manager.setAddress(managerAddress);
        manager.setSSN(398493);

        Employee newEmployee = new Employee();
        newEmployee.setName("Reyan Ali");
        Address newEmployeeAddress = new Address("43 fine lee", "Cambria", "California", 93428);
        newEmployee.setAddress(newEmployeeAddress);
        newEmployee.setSSN(11122333);
        newEmployee.setManage(manager);
        Employee.serializeObjectToFile(newEmployee, "employee.ser");

        Employee existingEmployee = null;
        existingEmployee = Employee.findEmployeeFromFilePath("employee.ser");
        Assert.assertEquals(existingEmployee.getName(), "Reyan Ali");
        Assert.assertEquals(existingEmployee.getAddress().address, "43 fine lee");
        Assert.assertEquals(existingEmployee.getSSN(), 11122333, .0001);

        Assert.assertEquals(existingEmployee.getManager().getName(), "Kilian Murphy");
        Assert.assertEquals(existingEmployee.getManager().getAddress().address, "23 green way");
        Assert.assertEquals(existingEmployee.getManager().getSSN(), 398493, .0001);

    }

}
