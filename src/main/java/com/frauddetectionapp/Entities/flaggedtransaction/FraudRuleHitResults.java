package com.frauddetectionapp.Entities.flaggedtransaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FraudRuleHitResults {
    String ruleName;
    String reason;
}
