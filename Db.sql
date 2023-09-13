--Database

CREATE DATABASE bibliotheque;

--Enum Type

CREATE TYPE public.statut_enum AS ENUM (
    'DISPONIBLE',
    'PÉRDU',
    'EMPRUNTER'
);

--Livre Table

CREATE TABLE
    livre (
        isbn character varying(255) NOT NULL,
        titre character varying(255) NOT NULL,
        auteur character varying(255) NOT NULL,
        statut statut_enum NOT NULL DEFAULT 'DISPONIBLE':: statut_enum,
        CONSTRAINT livre_isbn_key UNIQUE (isbn)
    );

--Member Table

CREATE TABLE
    member (
        membernémuro integer NOT NULL,
        membernom character varying(255) NOT NULL,
        membertelephone integer,
        membercin character varying(255),
        CONSTRAINT "member_membernémuro_key" UNIQUE ("membernémuro")
    );

--Emprunt Table

CREATE TABLE
    emprunt (
        livreisbn character varying(255) NOT NULL,
        "membernémuro" integer NOT NULL,
        datedemprunt date NOT NULL,
        datefindemprunt date NOT NULL,
        CONSTRAINT emprunt_livreisbn_fkey FOREIGN KEY (livreisbn) REFERENCES public.livre (isbn) ON DELETE CASCADE,
        CONSTRAINT "emprunt_membernémuro_fkey" FOREIGN KEY ("membernémuro") REFERENCES public.member ("membernémuro") ON DELETE CASCADE
    );

--Function UPDATE_LIVRE_STATUT

CREATE OR REPLACE FUNCTION PUBLIC.UPDATE_LIVRE_STATUT
() RETURNS TRIGGER LANGUAGE 'PLPGSQL' COST 100 VOLATILE 
NOT LEAKPROOF AS $BODY$ 
	$BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ BEGIN IF TG_OP = 'INSERT' THEN
	UPDATE livre
	SET statut = 'EMPRUNTER'
	WHERE isbn = NEW.livreisbn;
	ELSIF TG_OP = 'DELETE' THEN
	UPDATE livre
	SET statut = 'DISPONIBLE'
	WHERE isbn = OLD.livreisbn;
	END IF;
	RETURN NULL;
	END;
	$BODY$;


--Trigger update_livre_statut_trigger

CREATE TRIGGER update_livre_statut_trigger
    AFTER INSERT OR DELETE
    ON public.emprunt
    FOR EACH ROW
    EXECUTE FUNCTION public.update_livre_statut();


--Function update_perdu_livre

CREATE OR REPLACE FUNCTION PUBLIC.UPDATE_PERDU_LIVRE
() RETURNS VOID LANGUAGE 'PLPGSQL' COST 100 VOLATILE 
PARALLEL UNSAFE AS $BODY$ 
	$BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ $BODY$ BEGIN
	UPDATE livre l
	SET statut = 'PÉRDU'
	WHERE l.isbn IN (
	        SELECT livreisbn
	        FROM emprunt
	        WHERE
	            datefindemprunt < NOW()
	    );
END;
$BODY$;