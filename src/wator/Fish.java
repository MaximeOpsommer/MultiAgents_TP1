package wator;

public class Fish extends AquaticAnimal {
	
	public Fish(final WatorEnvironment env, final int line, final int column){
		super(env, line, column, ((WatorConfigs) env.getConfigs()).getFishBreedTime());
	}

	@Override
	public void decide() {
		
	}

	public WatorConfigs getConfigs() {
		return (WatorConfigs) env.getConfigs();
	}

}
