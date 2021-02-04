function updateBalance(r, amount)
    r['balance'] = r['balance'] + amount
    aerospike:update(r)
end