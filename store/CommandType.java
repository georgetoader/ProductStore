package store;

// Enum for identifying the commands easier
public enum CommandType {
    LISTCURRENCIES,
    GETSTORECURRENCY,
    ADDCURRENCY,
    LOADCSV,
    SAVECSV,
    SETSTORECURRENCY,
    UPDATEPARITY,
    LISTPRODUCTS,
    SHOWPRODUCT,
    LISTMANUFACTURERS,
    LISTPRODUCTSBYMANUFACTURER,
    LISTDISCOUNTS,
    ADDISCOUNT,
    APPLYDISCOUNT,
    CALCULATETOTAL,
    SAVESTORE,
    LOADSTORE,
    EXIT,
    QUIT
}