#!/usr/bin/env python3.5

import numpy
import matplotlib.pyplot as pyplot


def high_low_current_graph(high, low, current):
    ind = numpy.arange(1)
    width = 0.5
    fig, ax = pyplot.subplots()
    ax.bar(ind, high, width, color='r')
    ax.bar(ind + 1, current, width, color='g')
    ax.bar(ind + 2, low, width, color='b')

    ax.set_ylabel('Price')
    ax.set_title('52 Week Price Comparison')
    ax.set_xticks((0.25, 1.25, 2.25))
    ax.set_xticklabels(('52 Week High', 'Current', '52 Week low'))

    pyplot.show()


print(
    'This is a lightweight script for generating simple investing analytics.' +
    '\nPlease enter the following information as numerical values.\n')

while True:
    try:
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
        break
    except ValueError:
        print('You have entered a non-numerical character, ' +
              'please enter the information again')

try:
    price_per_book = (number_of_shares * share_price) / (
        total_assets - total_liabilities)
except ZeroDivisionError:
    price_per_book = 'undefined'
try:
    price_per_earnings = (number_of_shares * share_price) / net_income
except ZeroDivisionError:
    price_per_earnings = 'undefined'
try:
    dividend_percentage = dividends_payable / (
        number_of_shares * share_price)
except ZeroDivisionError:
    dividend_percentage = 'undefined'
try:
    dividend_safety = dividends_payable / net_income
except ZeroDivisionError:
    dividend_safety = 'undefined'

while True:
    print('The price/book is', price_per_book)
    print('The price/earnings is', price_per_earnings)
    print('The dividends percentage is', dividend_percentage)
    print('The dividend safety is', dividend_safety)
    high_low_current_graph(
        fifty_two_week_high, fifty_two_week_low, share_price)
    input('Press any key to quit')
    break
