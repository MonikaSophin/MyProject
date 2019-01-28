package com.thinkinginjava.type_information.exercise.chapter14_3;

import com.thinkinginjava.type_information.example.chapter14_4.Factory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part {
  public String toString() {
    return getClass().getSimpleName();
  }
  static List<Factory<? extends Part>> partFactories = new ArrayList<>();
  static {
    partFactories.add(new FuelFilter.Factory2());
    partFactories.add(new AirFilter.Factory2());
    partFactories.add(new CabinAirFilter.Factory2());
    partFactories.add(new OilFilter.Factory2());
    partFactories.add(new FanBelt.Factory2());
    partFactories.add(new PowerSteeringBelt.Factory2());
    partFactories.add(new GeneratorBelt.Factory2());
  }
  private static Random rand = new Random(47);
  public static Part createRandom() {
    int n = rand.nextInt(partFactories.size());
    return partFactories.get(n).create();
  }
}

class Filter extends Part {}

class FuelFilter extends Filter {
  // Create a Class Factory for each specific type:
  public static class Factory2 implements Factory<FuelFilter> {
    public FuelFilter create() { return new FuelFilter(); }
  }
}

class AirFilter extends Filter {
  public static class Factory2 implements Factory<AirFilter> {
    public AirFilter create() { return new AirFilter(); }
  }
}

class CabinAirFilter extends Filter {
  public static class Factory2 implements Factory<CabinAirFilter> {
    public CabinAirFilter create() {
      return new CabinAirFilter();
    }
  }
}

class OilFilter extends Filter {
  public static class Factory2 implements Factory<OilFilter> {
    public OilFilter create() { return new OilFilter(); }
  }
}

class Belt extends Part {}

class FanBelt extends Belt {
  public static class Factory2 implements Factory<FanBelt> {
    public FanBelt create() { return new FanBelt(); }
  }
}

class GeneratorBelt extends Belt {
  public static class Factory2 implements Factory<GeneratorBelt> {
    public GeneratorBelt create() {
      return new GeneratorBelt();
    }
  }
}

class PowerSteeringBelt extends Belt {
  public static class Factory2 implements Factory<PowerSteeringBelt> {
    public PowerSteeringBelt create() {
      return new PowerSteeringBelt();
    }
  }
}

public class RegisteredFactories {
  
}
/**output:
 * GeneratorBelt
 * CabinAirFilter
 * GeneratorBelt
 * AirFilter
 * PowerSteeringBelt
 * CabinAirFilter
 * FuelFilter
 * PowerSteeringBelt
 * PowerSteeringBelt
 * FuelFilter
 */