--
-- Created by IntelliJ IDEA.
-- User: zzjmay
-- Date: 2019/2/13
-- Time: 8:06 PM
-- To change this template use File | Settings | File Templates.
--
a = 100
print("hello world")

print(type(a))

tab1 = {key1 = "val1",key2="val2","val3","val4"}
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end
-- 将值赋值为nil相当于删除对应的元素
tab1.key1 = nil
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end
-- 判断一个值是否为nil
print(type(x)=="nil")

--字符串的表示双引号或者单引号
string1 = "this is string1"
string2 = "this is string2"

print(string1)
--表示一块字符串
html = [[
    Hello world,ahhaksk
    <br>
]]

print(html)

--数字运算
print("2"-"5")

--字符串的连接用的是..
print("2" .. "5")

--使用#计算字符串的长度
print(#string1)

-- table 关联数组,索引值从1开始
table1 = {"apple","java","python"}
for k, v in pairs(table1) do
    print(k.. ":".. v)
end

-- function 可以作为参数传递
function testFun(tab,fun)
    for k, v in pairs(tab) do
        print(fun(k,v));
    end
end

testTable ={key1 = "java1",key2="java2" }

testFun(testTable,function(k,v)
    return k.."_"..v
end)

-- Lua变量，必须在代码中进行声明，默认所有的都是全局变量
a = 5
local b =4

function jonk()
    c = 10
    local d = 100
end
jonk()

print(c,d)

-- 多变量赋值，必须一一对应,和java不同，如果没有赋值，默认是nil
a,b,c = 0
print(a,b,c)

-- 索引
site ={}
site["key2"] = "www.baidu.com"
print(site.key2)