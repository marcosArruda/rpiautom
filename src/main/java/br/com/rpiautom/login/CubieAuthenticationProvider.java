package br.com.rpiautom.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public class CubieAuthenticationProvider implements AuthenticationProvider{
	private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static
	{
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException
	{
		String user = auth.getName();
		String senha = auth.getCredentials().toString();
		if (testMarcos(user, senha)
		||  testOlivia(user, senha)
		||  testNewton(user, senha)
		||	testWaldemar(user, senha)
		||  testMae(user, senha))
		{
			return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
		}
		
		throw new BadCredentialsException("Bad User/Password");
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		if ( authentication == null ) return false;
	    return Authentication.class.isAssignableFrom(authentication);
	}
	
	private boolean testMarcos(String user, String senha)
	{
		return user.equals("marcos") && senha.equals("200510casa");
	}
	
	private boolean testOlivia(String user, String senha)
	{
		return user.equals("pecosa") && senha.equals("pecoso");
	}
	
	private boolean testNewton(String user, String senha)
	{
		return user.equals("newton") && senha.equals("n666");
	}
	
	private boolean testWaldemar(String user, String senha)
	{
		return user.equals("waldemar") && senha.equals("wal1293");
	}
	
	private boolean testMae(String user, String senha)
	{
		return user.equals("cristina") && senha.equals("biobedas");
	}
}
