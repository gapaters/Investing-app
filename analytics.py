#!/usr/bin/env python3.5

while True:
    number_of_shares = input('Enter the total number of shares floated: ')
    number_of_shares = int(number_of_shares)

    share_price = input('Enter the share price: ')
    share_price = float(share_price)

    fifty_two_week_high = input('Enter the 52 week high: ')
    fifty_two_week_high = float(fifty_two_week_high)

    fifty_two_week_low = input('Enter the 52 week low: ')
    fifty_two_week_low = float(fifty_two_week_low)

    total_assets = input('Enter the total assets: ')
    total_assets = int(total_assets)

    total_liabilities = input('Enter the total liabilities: ')
    total_liabilities = int(total_liabilities)

    net_income = input('Enter the net income over the last 12 months: ')
    net_income = int(net_income)

    dividends_payable = input('Enter the dividends payable: ')
    dividends_payable = int(dividends_payable)

    price_per_book = (
        number_of_shares * share_price) / (total_assets - total_liabilities)
    price_per_earnings = (number_of_shares * share_price) / net_income
    dividend_percentage = dividends_payable / (number_of_shares * share_price)
    dividend_safety = dividends_payable / net_income

    while True:
        print('The price/book is ', price_per_book)
        print('The price/earnings is', price_per_earnings)
        print('The dividends percentage is ', dividend_percentage)
        print('The dividend safety is', dividend_safety)
        input('Press any key to quit')
        break

    break
