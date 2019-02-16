--
-- Created by IntelliJ IDEA.
-- User: zzjmay
-- Date: 2019/2/16
-- Time: 4:05 PM
-- To change this template use File | Settings | File Templates.
--限制IP流量
--incr命令，key不存在，会先初始化为0，然后在执行incr操作
--获取key
local key = KEYS[1]
--过期时间，单位s
local expire = ARGV[1]
--限制的次数
local limitCount = ARGV[2]

local count = redis.call('incr',key)
--获取当前key的过期时间
local ttl = redis.call('ttl',key)


if count == 1 then
    --设置过期时间
    redis.call('expire',key,expire)
else
    if ttl == -1 then
        --说明存在未设置过期时间的限流key
        redis.call('expire',key,expire)
    end
end

-- 如果大于了限制的次数，就不返回0
if count > tonumber(ARGV[2]) then
    return 0
end

return 1