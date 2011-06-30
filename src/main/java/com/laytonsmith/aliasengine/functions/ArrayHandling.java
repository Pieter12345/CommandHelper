/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laytonsmith.aliasengine.functions;

import com.laytonsmith.aliasengine.CancelCommandException;
import com.laytonsmith.aliasengine.ConfigRuntimeException;
import com.laytonsmith.aliasengine.Constructs.CArray;
import com.laytonsmith.aliasengine.Constructs.CBoolean;
import com.laytonsmith.aliasengine.Constructs.CInt;
import com.laytonsmith.aliasengine.Constructs.CVoid;
import com.laytonsmith.aliasengine.Constructs.Construct;
import com.laytonsmith.aliasengine.Static;
import com.laytonsmith.aliasengine.functions.BasicLogic._equals;
import com.laytonsmith.aliasengine.functions.Exceptions.ExceptionType;
import org.bukkit.entity.Player;

/**
 *
 * @author Layton
 */
public class ArrayHandling {
    public static String docs(){
        return "This class contains functions that provide a way to manipulate arrays. To create an array, use the <code>array</code> function.";
    }
    @api public static class array_size implements Function{

        public String getName() {
            return "array_size";
        }

        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        public Construct exec(int line_num, Player p, Construct... args) throws CancelCommandException, ConfigRuntimeException {
            if(args[0] instanceof CArray){
                return new CInt(((CArray)args[0]).size(), line_num);
            }
            throw new ConfigRuntimeException("Argument 1 of array_size must be an array", ExceptionType.CastException, line_num);
        }
        
        public ExceptionType[] thrown(){
            return new ExceptionType[]{ExceptionType.CastException};
        }

        public String docs() {
            return "int {array} Returns the size of this array as an integer";
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }

        public String since() {
            return "3.0.1";
        }

        public Boolean runAsync() {
            return null;
        }
        
    }
    
    @api public static class array_get implements Function{

        public String getName() {
            return "array_get";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public Construct exec(int line_num, Player p, Construct... args) throws CancelCommandException, ConfigRuntimeException {
            if(args[0] instanceof CArray){
                return ((CArray)args[0]).get((int)Static.getInt(args[1]), line_num);
            } else{
                throw new ConfigRuntimeException("Argument 1 of array_get must be an array", ExceptionType.CastException, line_num);
            }
        }
        
        public ExceptionType[] thrown(){
            return new ExceptionType[]{ExceptionType.CastException, ExceptionType.IndexOverflowException};
        }

        public String docs() {
            return "mixed {array, index} Returns the element specified at the index of the array. If the element doesn't exist, an exception is thrown. "
                    + "array_get(array, index)";
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }
        public String since() {
            return "3.0.1";
        }
        
        public Boolean runAsync() {
            return null;
        }
        
    }
    
    @api public static class array_set implements Function{

        public String getName() {
            return "array_set";
        }

        public Integer[] numArgs() {
            return new Integer[]{3};
        }

        public Construct exec(int line_num, Player p, Construct... args) throws CancelCommandException, ConfigRuntimeException {
            if(args[0] instanceof CArray && args[1] instanceof CInt){
                ((CArray)args[0]).set((int)((CInt)args[1]).getInt(), args[2]);
                return new CVoid(line_num);
            }
            throw new ConfigRuntimeException("Argument 1 of array_set must be an array, and argument 2 must be an integer", ExceptionType.CastException, line_num);        
        }

        public ExceptionType[] thrown(){
            return new ExceptionType[]{ExceptionType.CastException};
        }
        
        public String docs() {
            return "void {array, index, value} Sets the value of the array at the specified index. array_set(array, index, value). Returns void. If"
                    + " the element at the specified index isn't already set, throws an exception. Use array_push to avoid this.";
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }
        public String since() {
            return "3.0.1";
        }
        
        public Boolean runAsync() {
            return null;
        }
    }
    
    @api public static class array_push implements Function{

        public String getName() {
            return "array_push";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public Construct exec(int line_num, Player p, Construct... args) throws CancelCommandException, ConfigRuntimeException {
            if(args[0] instanceof CArray){
                ((CArray)args[0]).push(args[1]);
                return new CVoid(line_num);
            }
            throw new ConfigRuntimeException("Argument 1 of array_push must be an array", ExceptionType.CastException, line_num);
        }
        
        public ExceptionType[] thrown(){
            return new ExceptionType[]{ExceptionType.CastException};
        }

        public String docs() {
            return "void {array, value} Pushes the specified value onto the end of the array";
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }
        public String since() {
            return "3.0.1";
        }
        
        public Boolean runAsync() {
            return null;
        }
        
    }
    @api public static class array_contains implements Function {

        public String getName() {
            return "array_contains";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public Construct exec(int line_num, Player p, Construct... args) throws CancelCommandException, ConfigRuntimeException {
            _equals e = new _equals();
            if(args[0] instanceof CArray){
                CArray ca = (CArray) args[0];
                for(int i = 0; i < ca.size(); i++){
                    if(((CBoolean)e.exec(line_num, p, ca.get(i, line_num), args[1])).getBoolean()){
                        return new CBoolean(true, line_num);
                    }
                }
                return new CBoolean(false, line_num);
            } else {
                throw new ConfigRuntimeException("Argument 1 of array_contains must be an array", ExceptionType.CastException, line_num);
            }
        }
        
        public ExceptionType[] thrown(){
            return new ExceptionType[]{ExceptionType.CastException, ExceptionType.IndexOverflowException};
        }

        public String docs() {
            return "boolean {array, testValue} Checks to see if testValue is in array.";
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }
        public String since() {
            return "3.0.1";
        }
        
        public Boolean runAsync() {
            return null;
        }
        
    }
    
    @api public static class array_index_exists implements Function{

        public String getName() {
            return "array_index_exists";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public String docs() {
            return "boolean {array, index} Checks to see if the specified array has an element at index";
        }

        public ExceptionType[] thrown() {
            return new ExceptionType[]{ExceptionType.CastException};
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }

        public String since() {
            return "3.1.2";
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(int line_num, Player p, Construct... args) throws ConfigRuntimeException {
            if(args[0] instanceof CArray){
                int index = (int)Static.getInt(args[1]);
                CArray ca = (CArray)args[0];
                return new CBoolean(index <= ca.size() - 1, line_num);
            } else {
                throw new ConfigRuntimeException("Expecting argument 1 to be an array", ExceptionType.CastException, line_num);
            }
        }
        
    }
}
