-- 控制语句 for ,if等

-- for循环,数值循环，如果是函数，一定是在循环开始前进行的
function f(x)
    print("function")
    return x * 2;
end

-- 这里数值指的是0-4的范围，表达式就计算一次
for i = 0, f(2) do
    print(i)
end

-- 范型输出

days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }

for k, v in pairs(days) do
    print(v)
end

-- if语句
a = 198

if a < 100 then
    print("a小于100")
elseif a >= 100 then
    print("a>=100")
end
-- function 基本函数
function max(a,b)
    if a > b then
        result = a
    else
        result = b
    end
    return result;
end

print("找出两数的最大值:",max(4,8))

--function 函数作为参数传递
function add(num1,num2,fun)
    result = num1 + num2
    fun(result)
end

function myprint(param)
    print("我的打印函数",param)
end
-- 函数式编程，将myprint作为参数进行传递
add(9,10,myprint)

-- 函数的可变参数用...

function add(...)
    local s= 0
    -- 将可变参数赋值给变量
    local num ={...}
    print("当前可变参数的个数："..#num)
    for k, v in pairs{...} do
        s= s +v
    end
    return s
end

print(add(1,2,4,56))

--字符串
print(string.format("the value is :%d",4))

