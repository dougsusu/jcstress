package com.sch.jcstress;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;


@JCStressTest

// java -XX:+UnlockDiagnosticVMOptions -XX:+WhiteBoxAPI -XX:-RestrictContended -jar target/jcstress-learning.jar -v -t OotaTest
@Outcome(id = "42, 0", expect = Expect.ACCEPTABLE, desc = "42,0")
@Outcome(id = "42, 42", expect = Expect.ACCEPTABLE_INTERESTING, desc = "42,42")
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE, desc = "0,0")
@Outcome(id = "0, 42", expect = Expect.ACCEPTABLE, desc = "0,42")

// This is a state object
@State
public class OotaTest {

    int a = 0 , b = 0;

    @Actor
    public void actor1(II_Result r) {
        b = 42;
        r.r1 = a;
        if(r.r1 == 0){
            b = 0;
        }
        r.r2 = b;
    }

    @Actor
    public void actor2(II_Result r) {
        r.r2 = b;
        if(r.r2 != 0){
            a = 42;
        }
        r.r1 = a;
    }

}