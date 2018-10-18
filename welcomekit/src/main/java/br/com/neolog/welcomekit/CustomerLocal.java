package br.com.neolog.welcomekit;

import br.com.neolog.welcomekit.models.Customer;

public class CustomerLocal
{
    private static final ThreadLocal<Customer> threadLocal = new ThreadLocal<>();

    public static Customer getCurrentCustomer()
    {
        return threadLocal.get();
    }

    public static void setCurrentCustomer(
        final Customer customer )
    {
        threadLocal.set( customer );;
    }

    public static void removeCurrentCustomer()
    {
        threadLocal.remove();
    }

}
