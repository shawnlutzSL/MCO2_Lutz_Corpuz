package com.example.mco2_supermarketsimulator;

import java.util.Arrays;
import java.util.List;

/**
 * These are the different types of brands for the productTypes, hence a BREAD can come from "Crustory" or "LoafLuxe".
 */
public enum Brand {
    FRUIT(Arrays.asList("JuiceMingle", "BloomVale", "Sunroot")),
    VEGETABLE(Arrays.asList("Vegspire", "GreenPatch", "RootRise")),
    BREAD(Arrays.asList("Crustory", "Bakeridge", "LoafLuxe")),
    EGGS(Arrays.asList("Shellora", "Eggspire", "GoldenYolk")),
    MILK(Arrays.asList("DairyMist", "VelvoMoo", "CreamCrest")),
    FROZEN_FOOD(Arrays.asList("Frostella", "ChillCraft", "CoolFeast")),
    CHEESE(Arrays.asList("Curdify", "MeltHaven", "Cheezor")),
    CHICKEN(Arrays.asList("Henvale", "FeatherFord", "RoostRidge")),
    BEEF(Arrays.asList("RedRange", "CattleCore", "BeefLoom")),
    SEAFOOD(Arrays.asList("DeepCatch", "Tideborne", "AquaVale")),
    CEREAL(Arrays.asList("CrunchRidge", "MornBowl", "Oatnova")),
    NOODLES(Arrays.asList("Slurpify", "TwistNest", "PastaNova")),
    SNACKS(Arrays.asList("BiteBurst", "Snacklet", "Chompley")),
    CANNED_GOODS(Arrays.asList("Preservia", "Canistry", "TinVale")),
    CONDIMENTS(Arrays.asList("SaucyVale", "Spreadify", "ZestCrafter")),
    SOFT_DRINK(Arrays.asList("Fizzen", "SodaNova", "Poporium")),
    JUICE(Arrays.asList("SqueezeVale", "VitaPour", "Pulpress")),
    ALCOHOL(Arrays.asList("Oakspire", "Brewvale", "Spirist")),
    CLEANING_AGENTS(Arrays.asList("Scrubwell", "BrightMist", "ShinyEase")),
    HOME_ESSENTIALS(Arrays.asList("Homely", "NestEase", "CasaCore")),
    HAIR_CARE(Arrays.asList("Sheenify", "Glossen", "StrandMist")),
    BODY_CARE(Arrays.asList("Bodiq", "Soothelux", "Luminex")),
    DENTAL_CARE(Arrays.asList("Mintara", "BrightVale", "Dentova")),
    CLOTHES(Arrays.asList("Threadora", "Wearly", "ModaRise")),
    STATIONERY(Arrays.asList("Inknote", "Paperium", "Scribra")),
    PET_FOOD(Arrays.asList("Pawtica", "Furvale", "TailTreat"));

    private final List<String> brands;
    Brand(List<String> brands) {
        this.brands = brands;
    }
    public List<String> getBrands() {return brands;}

}
