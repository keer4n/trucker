package io.github.keer4n.trucker.rules;

import io.github.keer4n.trucker.entity.Reading;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

public class TruckerRules {

    public void setup(Reading reading){
        Facts facts = extractFact(reading);
        Rules rules = new Rules();
        Rule rpmRule = new RuleBuilder()
                .name("RPM rule")
                .description("Triggered when the engine RPM is greater than redLineRPM")
                .when(f -> (int)f.get("engineRpm") > (int)facts.get("redLineRpm"))
                .then(f -> System.out.println("kiran"))
                .build();
        rules.register(rpmRule);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
    }

    public Facts extractFact(Reading reading){
        Facts facts = new Facts();
        facts.put("vin",reading.getVin());
        facts.put("engineRpm",reading.getEngineRpm());
        return facts;
    }
}
