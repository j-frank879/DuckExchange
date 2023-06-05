package com.example.duckexchange;

import java.util.List;

public class BoughtCurrencyStorage {

private BoughtCurrencyStorage instance=null;
    private List<BoughtCurrency> storage=null;

private BoughtCurrencyStorage(){
    read();
}

    public BoughtCurrencyStorage getInstance()
    {if(instance==null)
    {instance=new BoughtCurrencyStorage();

    }
        return instance;
    }
public void read()
{//wczytanie danych z pamieci

}
    public void save()
    {//zapisanie danych w pamieci

    }
public void add(BoughtCurrency c)
{storage.add(c);

}
public void delete(int id)
{storage.remove(id);
}

}
