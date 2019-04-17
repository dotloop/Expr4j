package org.boris.expr.function.excel;

import java.math.BigDecimal;

import org.boris.expr.Expr;
import org.boris.expr.ExprDecimal;
import org.boris.expr.ExprError;
import org.boris.expr.ExprException;
import org.boris.expr.ExprMissing;
import org.boris.expr.ExprNumber;
import org.boris.expr.ExprType;
import org.boris.expr.IEvaluationContext;
import org.boris.expr.function.AbstractFunction;
import org.boris.expr.util.PowerUtil;

public class POWER extends AbstractFunction
{
    public Expr evaluate(IEvaluationContext context, Expr[] args)
            throws ExprException {
        assertMinArgCount(args,2);
        assertMaxArgCount(args, 3);

        Expr expr0 = evalArg(context, args[0]);
        Expr expr1 = evalArg(context, args[1]);

        boolean isStrict = args.length == 3 && args[2] != null && "strict".equals(args[2].toString());

        if (isStrict) {
            if (expr0.getType() == ExprType.Missing && expr1.getType() == ExprType.Missing) {
                return new ExprMissing();
            }
        } else {
            if (expr0.getType() == ExprType.Missing || expr1.getType() == ExprType.Missing) {
                return new ExprMissing();
            }
        }

        return PowerUtil.evaluate(asDecimal(context, expr0, true), asDecimal(context, expr1, true).intValue());
    }
}
