package org.boris.expr.util;

import java.math.BigDecimal;

import org.boris.expr.Expr;
import org.boris.expr.ExprDecimal;
import org.boris.expr.ExprError;
import org.boris.expr.ExprNumber;

public class PowerUtil {
    public static Expr evaluate(BigDecimal lhs, int rhs) {
        try {
            BigDecimal result = lhs
                    .pow(rhs, ExprDecimal.MATH_CONTEXT);
            if (Double.isInfinite(result.doubleValue())) {
                return ExprError.generateError(ExprError.OVERFLOW);
            } else {
                return new ExprDecimal(result);
            }
        } catch (ArithmeticException e) {
            return ExprError.generateError(ExprError.OVERFLOW);
        }
    }
}