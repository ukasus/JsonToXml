package javaxmlMap;

public class data {
    public String RoomTypeID;
    public String RateTypeID;
    public String FromDate;
    public String ToDate;
    public boolean Taxinclusive;
    public roomRate RoomRate;

    public data()
    {
        RoomRate=new roomRate();
    }
}
