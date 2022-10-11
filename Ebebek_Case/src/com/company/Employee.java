package com.company;

public class Employee {
    private final String name;
    private double salary;
    private int workHours;
    private final int hireYear;

    public Employee(String name, double salary, int workHours, int hireYear) {
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
    }

    public double tax() {
        if (salary > 1000)
            return (salary * 3) / 100;
        return 0;
    }

    public int bonus() {
        if (workHours > 40)
            return (workHours - 40) * 30;
        return 0;
    }

    public double raiseSalary() {
        int workYear = 2021 - hireYear;
        double increaseAmount = 0;

        if (workYear < 10)
            increaseAmount = (salary * 0.05) + bonus() - tax() * 1.05; // if the salary goes up, the tax must go up as well.
        else if (workYear < 20) //  already know that workYear is bigger than 10 years, so I didn't check it again.
            increaseAmount = (salary * 0.1) + bonus() - (tax() * 1.1);
        else
            increaseAmount = (salary * 0.15) + bonus() - (tax() * 1.15);

        salary += increaseAmount;
        return increaseAmount;
    }

    public String toString() {
        return "Name: " + name + "\nSalary: " + salary + "\nWork hours: " + workHours + "\nHire year: " + hireYear + "\nTax: " + tax() +"\nBonus: " + bonus() + "\nRaise salary: " + raiseSalary() + "\nSalary with taxes and bonuses: " + (bonus() - tax() + salary) + "\nTotal salary: " + (bonus() + salary);
    }


}
