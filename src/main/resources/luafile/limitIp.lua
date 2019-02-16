--
-- Created by IntelliJ IDEA.
-- User: zzjmay
-- Date: 2019/2/16
-- Time: 4:05 PM
-- To change this template use File | Settings | File Templates.
--限制IP流量
--incr命令，key不存在，会先初始化为0，然后在执行incr操作
local count = redis.call('incr',KEYS[1])

if count == 1 then
    --设置过期时间
    redis.call('expire',KEYS[1],ARGV[1])
end

-- 如果大于了限制的次数，就不返回0
if count > tonumber(ARGV[2]) then
    return 0
end

return 1