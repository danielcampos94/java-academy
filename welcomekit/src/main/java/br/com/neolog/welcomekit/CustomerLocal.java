package br.com.neolog.welcomekit;

public class CustomerLocal
{
    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static int getCurrentCustomerId()
    {
        return threadLocal.get();
    }

    public static void setCurrentCustomerId(
        final int id )
    {
        threadLocal.set( id );;
    }

    public static void removeCurrentCustomerId()
    {
        threadLocal.remove();
    }

}
