--测试模块

module ={}

module.constant = "这是一个常量"

function module.func1()
    print("这是一个公有函数")
end

local function func2()
    print("私有函数")
end

function module.func3()
    func2()
end

return module