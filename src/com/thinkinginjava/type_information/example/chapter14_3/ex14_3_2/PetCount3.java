package com.thinkinginjava.type_information.example.chapter14_3.ex14_3_2;

import com.thinkinginjava.type_information.example.chapter14_3.ex14_3_1.LiteralPetCreator;
import com.thinkinginjava.type_information.example.chapter14_3.ex14_3_1.Pets;
import com.thinkinginjava.type_information.example.chapter14_3.ex14_3_2.utils.MapData;
import com.thinkinginjava.type_information.example.chapter14_3.pets.Pet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: monika
 * @Date: 2019/1/26 17:49
 * @Version: 1.0
 * @Description:
 */
public class PetCount3 {
    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes, 0));
        }
        public void count(Pet pet) {
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                if (pair.getKey().isInstance(pet))
                    put(pair.getKey(),pair.getValue() + 1);
            }
        }
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("{");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet()) {
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append("}");
            return result.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter petCount = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            petCount.count(pet);
        }
        System.out.println();
        System.out.print(petCount);
    }
}
/**output:
 * Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug Mouse Cymric
 * {Pet=20, Dog=6, Cat=9, Rodent=5, Mutt=3, Pug=3, EgyptianMau=2, Manx=7, Cymric=5, Rat=2, Mouse=2, Hamster=1}
 */