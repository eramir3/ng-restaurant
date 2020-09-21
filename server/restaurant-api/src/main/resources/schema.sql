create or replace function get_profitable_clients ()
	returns table (
		first_name varchar,
		last_name varchar,
		total_price double precision
	)
	language plpgsql
as
$$
begin
	return query
		SELECT * FROM
			(
				SELECT client.first_name, client.last_name, SUM(price) as total_price
				FROM bill_detail bd
				JOIN bill b ON b.id = bd.bill_id
				JOIN client ON client.id = b.client_id
				GROUP BY client.id
			) AS result
		WHERE result.total_price > 100;
end;$$




CREATE OR REPLACE FUNCTION public.get_waiters_total_billed(year character varying)
 RETURNS TABLE(
 		first_name varchar,
		last_name varchar,
		total_price double precision,
		january double precision,
        february double precision,
        march double precision,
        april double precision,
        may double precision,
        june double precision,
        july double precision,
        august double precision,
        september double precision,
        october double precision,
        november double precision,
        december double precision)
 LANGUAGE plpgsql
AS $$
begin
	return query

		select w.first_name, w.last_name, sum(bd.price) as total_billed,
	    SUM(CASE WHEN created_at >= to_date(concat(year, '-01-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-01-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS january,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-02-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-02-28'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS february,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-03-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-03-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS march,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-04-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-04-30'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS april,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-05-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-05-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS may,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-06-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-06-30'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS june,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-07-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-07-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS july,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-08-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-08-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS august,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-09-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-09-30'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS september,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-10-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-10-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS october,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-11-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-11-30'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS november,
		SUM(CASE WHEN created_at >= to_date(concat(year, '-12-01'),'YYYY-MM-DD') AND created_at <= to_date(concat(year, '-12-31'),'YYYY-MM-DD') THEN bd.price ELSE 0 END) AS december
		from waiter w
		join bill b on b.waiter_id = w.id
		join bill_detail bd on bd.bill_id = b.id
		group by w.id;

end;$$









-------------- FOR SPRING BOOT START UP ------------------
create or replace function get_profitable_clients ()
	returns table (
		first_name varchar,
		last_name varchar,
		total_price double precision
	)
	language plpgsql
as ' DECLARE
begin
	return query

		SELECT * FROM
			(
				SELECT client.first_name, client.last_name, SUM(price) as total_price
				FROM bill_detail bd
				JOIN bill b ON b.id = bd.bill_id
				JOIN client ON client.id = b.client_id
				GROUP BY client.id
			) AS result
		WHERE result.total_price > 100;

end; ';

