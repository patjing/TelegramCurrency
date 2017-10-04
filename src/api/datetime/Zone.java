package api.datetime;

public enum Zone {
	UTCM12(-12),
	UTCM11(-11),
	UTCM10(-10),
	UTCM9(-9),
	UTCM8(-8),
	UTCM7(-7),
	UTCM6(-6),
	UTCM5(-5),
	UTCM4(-4),
	UTCM3(-3),
	UTCM2(-2),
	UTCM1(-1),
	
	UTC0(0),
	
	UTCP14(14),
	UTCP13(13),
	UTCP12(12),
	UTCP11(11),
	UTCP10(10),
	UTCP9(9),
	UTCP8(8),
	UTCP7(7),
	UTCP6(6),
	UTCP5(5),
	UTCP4(4),
	UTCP3(3),
	UTCP2(2),
	UTCP1(1);
	
	private final int zone;
	
	Zone(int number)
	{
		this.zone = number;
	}
	
	
	public int value()
	{
		return this.zone;
	}
}
