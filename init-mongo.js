print('Start #################################################################');
db.createUser(
    {
        user: 'jose',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'osp' }],
    },
);
db.createCollection('shipments');
print('END #################################################################');