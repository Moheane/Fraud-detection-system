package com.frauddetectionapp.Entities.flaggedtransaction;

public enum Risk {
    LOW,    // total points < 33
    MEDIUM, // total points 33–66
    HIGH    // total points > 66
}