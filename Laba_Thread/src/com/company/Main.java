package com.company;


public class Main		//Класс с методом main().
{
    public static void main(String[] args)
    {
        //Создание потока
        int a = (int) (Math.random()*(200+1)) - 100;
        System.out.println("Главный поток ...");
        System.out.println("Генерируемое число = " +a);
        Thread myThready = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                if (a%2!=0)
                {
                    System.out.println("Привет из побочного потока! ");
                    System.out.println("Число нечетное");
                }
                else System.out.println("Вот непруха число четное!");
            }
        });
        myThready.start();	//Запуск потока

    }
}