// Runs automatically on first container start (docker-entrypoint-initdb.d).
// Extra idempotency guard so re-attaching a pre-populated volume is safe.

db = db.getSiblingDB('bank_account');

if (db.bank_account.countDocuments({}) > 0) {
    print('[seed] Collection already contains data – skipping.');
    quit();
}

const now      = new Date();
const daysAgo  = (n) => new Date(now.getTime() - n * 86_400_000);

db.bank_account.insertMany([
    {
        accountNumber: 'FR001234567890', ownerName: 'Alice Martin',
        balance: NumberDecimal('2450.75'), currency: 'EURO',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(180), updatedAt: daysAgo(5), version: NumberLong(0)
    },
    {
        accountNumber: 'US002345678901', ownerName: 'Bob Thompson',
        balance: NumberDecimal('15200.00'), currency: 'USD',
        accountType: 'SAVINGS', status: 'ACTIVE',
        createdAt: daysAgo(365), updatedAt: daysAgo(10), version: NumberLong(0)
    },
    {
        accountNumber: 'ES003456789012', ownerName: 'Carlos Rodriguez',
        balance: NumberDecimal('890.50'), currency: 'EURO',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(90), updatedAt: daysAgo(2), version: NumberLong(0)
    },
    {
        accountNumber: 'GB004567890123', ownerName: 'Diana Chen',
        balance: NumberDecimal('32000.00'), currency: 'GBP',
        accountType: 'SAVINGS', status: 'ACTIVE',
        createdAt: daysAgo(500), updatedAt: daysAgo(30), version: NumberLong(0)
    },
    {
        accountNumber: 'FR005678901234', ownerName: 'Emmanuel Dupont',
        balance: NumberDecimal('1200.00'), currency: 'EURO',
        accountType: 'CHECKING', status: 'SUSPENDED',
        createdAt: daysAgo(200), updatedAt: daysAgo(15), version: NumberLong(2)
    },
    {
        accountNumber: 'US006789012345', ownerName: 'Fatima Al-Hassan',
        balance: NumberDecimal('5670.25'), currency: 'USD',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(120), updatedAt: daysAgo(1), version: NumberLong(0)
    },
    {
        accountNumber: 'GB007890123456', ownerName: 'George Williams',
        balance: NumberDecimal('8900.00'), currency: 'GBP',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(730), updatedAt: daysAgo(60), version: NumberLong(1)
    },
    {
        accountNumber: 'DE008901234567', ownerName: 'Helena Novak',
        balance: NumberDecimal('42000.00'), currency: 'EURO',
        accountType: 'SAVINGS', status: 'ACTIVE',
        createdAt: daysAgo(1000), updatedAt: daysAgo(90), version: NumberLong(4)
    },
    {
        accountNumber: 'US009012345678', ownerName: 'Ibrahim Osei',
        balance: NumberDecimal('0.00'), currency: 'USD',
        accountType: 'CHECKING', status: 'CLOSED',
        createdAt: daysAgo(400), updatedAt: daysAgo(45), version: NumberLong(3)
    },
    {
        accountNumber: 'FR010123456789', ownerName: 'Julia Santos',
        balance: NumberDecimal('3400.50'), currency: 'EURO',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(60), updatedAt: daysAgo(3), version: NumberLong(0)
    },
    {
        accountNumber: 'GB011234567890', ownerName: "Kevin O'Brien",
        balance: NumberDecimal('12500.00'), currency: 'GBP',
        accountType: 'SAVINGS', status: 'ACTIVE',
        createdAt: daysAgo(300), updatedAt: daysAgo(20), version: NumberLong(1)
    },
    {
        accountNumber: 'DE012345678901', ownerName: 'Laura Schmidt',
        balance: NumberDecimal('7800.00'), currency: 'EURO',
        accountType: 'SAVINGS', status: 'SUSPENDED',
        createdAt: daysAgo(250), updatedAt: daysAgo(7), version: NumberLong(2)
    },
    {
        accountNumber: 'US013456789012', ownerName: 'Mohammed Al-Farsi',
        balance: NumberDecimal('9200.75'), currency: 'USD',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(150), updatedAt: daysAgo(4), version: NumberLong(0)
    },
    {
        accountNumber: 'FR014567890123', ownerName: 'Nadia Kowalski',
        balance: NumberDecimal('560.00'), currency: 'EURO',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(30), updatedAt: daysAgo(1), version: NumberLong(0)
    },
    {
        accountNumber: 'US015678901234', ownerName: 'Oliver Johnson',
        balance: NumberDecimal('28000.00'), currency: 'USD',
        accountType: 'SAVINGS', status: 'ACTIVE',
        createdAt: daysAgo(800), updatedAt: daysAgo(50), version: NumberLong(5)
    },
    {
        accountNumber: 'FR016789012345', ownerName: 'Patricia Leclerc',
        balance: NumberDecimal('18500.00'), currency: 'EURO',
        accountType: 'SAVINGS', status: 'ACTIVE',
        createdAt: daysAgo(450), updatedAt: daysAgo(12), version: NumberLong(2)
    },
    {
        accountNumber: 'GB017890123456', ownerName: 'Rashid Patel',
        balance: NumberDecimal('3200.00'), currency: 'GBP',
        accountType: 'CHECKING', status: 'SUSPENDED',
        createdAt: daysAgo(220), updatedAt: daysAgo(8), version: NumberLong(1)
    },
    {
        accountNumber: 'US018901234567', ownerName: 'Sophia Anderson',
        balance: NumberDecimal('6750.00'), currency: 'USD',
        accountType: 'CHECKING', status: 'ACTIVE',
        createdAt: daysAgo(75), updatedAt: daysAgo(6), version: NumberLong(0)
    },
    {
        accountNumber: 'DE019012345678', ownerName: 'Thomas Muller',
        balance: NumberDecimal('0.00'), currency: 'EURO',
        accountType: 'CHECKING', status: 'CLOSED',
        createdAt: daysAgo(600), updatedAt: daysAgo(180), version: NumberLong(6)
    },
    {
        accountNumber: 'US020123456789', ownerName: 'Victoria Lee',
        balance: NumberDecimal('450.00'), currency: 'USD',
        accountType: 'SAVINGS', status: 'CLOSED',
        createdAt: daysAgo(350), updatedAt: daysAgo(100), version: NumberLong(3)
    }
]);

print('[seed] Inserted 20 bank accounts successfully.');
