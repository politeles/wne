/**
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Jose Enrique Pons Frias
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * 
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.google.android.wne;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

import android.util.Log;

import com.google.android.wne.dataAccess.MotorConsultas;
import com.google.android.wne.mjeInterface.Mje2wneOntologyImpl;
import com.google.android.wne.mjeInterface.MjeBundle;
import com.google.android.wne.mjeInterface.admite;
import com.google.android.wne.mjeInterface.direccion;
import com.google.android.wne.mjeInterface.restaurante;
import com.google.android.wne.mjeInterface.tarjetas;
import com.google.android.wne.mjeInterface.tipoCocina;
/**
 * @author Jose Enrique
 *
 */
public class DataHandler implements ContentHandler{
protected List<admite> listaAdmite;
protected List<direccion> listaDireccion;
protected List<restaurante> listaRestaurante;
protected List<tarjetas> listaTarjetas;
protected List<tipoCocina> listaTipoCocina;
protected int tabla;    
protected boolean dentro_tabla;
protected String nombre_campo; /** Indico el nombre del nombre_campo de la tabla  */
protected admite Admite;
protected direccion Dir;
protected restaurante Res;
protected tarjetas Tarj;
protected tipoCocina TiCo;

protected MotorConsultas motorConsultas;

/**
 * @param motorConsultas the motorConsultas to set
 */
public void setMotorConsultas(MotorConsultas motorConsultas) {
	this.motorConsultas = motorConsultas;
}
/**
 * 
 */
	public void startDocument(){
		listaAdmite = new ArrayList<admite>();
		listaDireccion = new ArrayList<direccion>();
		listaRestaurante = new ArrayList<restaurante>();
		listaTarjetas = new ArrayList<tarjetas>();
		listaTipoCocina = new ArrayList<tipoCocina>();
		Admite = new admite();
		Dir = new direccion();
		Res = new restaurante();
		Tarj = new tarjetas();
		TiCo = new tipoCocina();
		
		
			
	//	Log.v("EX","principio");

	}
	public void processingInstruction(String target,String data){
		
	}
	public void characters(char[] ch, int start, int length){
		String contenido=null;
		try{
		//	Log.v("XML",new String(ch,start,length));
			contenido = new String(ch, start, length);
          if(dentro_tabla){
              //si he encontrado alguna de las tablas
              switch(tabla){
                  case 0://dentro de la tabla admite:
                 //     Vm.debug("Funcion Conenido: vivajana "+contenido);
                      if(nombre_campo.compareTo("NombreTarj")==0){
                          Admite.setNombreT(contenido);
                      //     Vm.debug("Contenenido del campo nombre tarjeta: "+contenido);
                      }else if(nombre_campo.compareTo("NomRes")==0){
                          Admite.setNombreRes(contenido);
                          // Vm.debug("Contenenido del campo nombre restaurante: "+contenido);
                      }
                      break;
                  case 1://dentro de la tabla restaurante
                      if(nombre_campo.compareTo("Nombre")==0){
                              Res.nombre = contenido;
                              
                         }else if(nombre_campo.compareTo("Ds")==0){
                              Res.descripcion = contenido;
                         }else if(nombre_campo.compareTo("Dir")==0){
                              Res.direccion = contenido;
                         }else if(nombre_campo.compareTo("Pmin")==0){
                              Res.pMin = contenido;
                         }else if(nombre_campo.compareTo("Pmax")==0){
                              Res.pMax = contenido;
                         }else if(nombre_campo.compareTo("Pavg")==0){
                              Res.pAvg = contenido;
                         }else if(nombre_campo.compareTo("tcocina")==0){
                              Res.tipoCocina = contenido;
                         }else if(nombre_campo.compareTo("Fum")==0){
                              Res.fum = contenido;
                         }else if(nombre_campo.compareTo("Him")==0){
                              Res.Him = contenido;
                         }else if(nombre_campo.compareTo("Hfm")==0){
                              Res.Hfm = contenido;
                         }else if(nombre_campo.compareTo("Hit")==0){
                              Res.Hit = contenido;
                         }else if(nombre_campo.compareTo("Hft")==0){
                              Res.Hft = contenido;
                         }else if(nombre_campo.compareTo("Dd")==0){
                              Res.Dd = contenido;
                         }else if (nombre_campo.compareTo("menu")==0){
                              Res.menu = contenido;
                         }else if(nombre_campo.compareTo("soles")==0){
                              Res.soles = contenido;
                         }else if(nombre_campo.compareTo("acc")==0){
                              Res.Acc = contenido;
                         }else if(nombre_campo.compareTo("Sp")==0){
                              Res.Sp = contenido;
                         }else if(nombre_campo.compareTo("Ap")==0){
                              Res.Ap = contenido;
                         }else if(nombre_campo.compareTo("ApPort")==0){
                              Res.ApPort = contenido;
                         }else if(nombre_campo.compareTo("Tj")==0){
                              Res.tj = contenido;
                         }else if(nombre_campo.compareTo("AiC")==0){
                              Res.aic = contenido;
                         }else if(nombre_campo.compareTo("Bd")==0){
                              Res.bd = contenido;
                         }else if(nombre_campo.compareTo("Lp")==0){
                              Res.lp = contenido;
                         }else if(nombre_campo.compareTo("ni")==0){
                              Res.ni = contenido;
                         }
                      break;
                  case 2://dentro de la tabla direccion
                       if(nombre_campo.compareTo("Id_direccion")==0){
                              Dir.idDireccion = contenido;
                          }else if(nombre_campo.compareTo("Calle")==0){
                              Dir.calle = contenido;
                          }else if(nombre_campo.compareTo("Numero")==0){
                              Dir.numero = contenido;
                          }else if(nombre_campo.compareTo("Ciudad")==0){
                              Dir.ciudad = contenido;
                          }else if(nombre_campo.compareTo("Provincia")==0){
                              Dir.provincia = contenido;
                          }else if(nombre_campo.compareTo("Telefono")==0){
                              Dir.Telefono = contenido;
                          }else if(nombre_campo.compareTo("C.P.")==0){
                              Dir.codP = contenido;
                          }else if(nombre_campo.compareTo("Coord_x")==0){
                              Dir.coordX = contenido;
                          }else if(nombre_campo.compareTo("Coord_y")==0){
                              Dir.coordY = contenido;
                          }else if(nombre_campo.compareTo("Zona_horaria")==0){
                              Dir.zonaH = contenido;
                          }
                      break;
                  case 3://tabla de tipo cocina
                          if(nombre_campo.compareTo("Nombre")==0){
                              TiCo.nombre = contenido;
                          }else if(nombre_campo.compareTo("Tipo")==0){
                              TiCo.tipo = contenido;
                          }
                      break;
                  case 4://tabla de tarjetas
                          if(nombre_campo.compareTo("Nombre")==0){
                              Tarj.nombre = contenido;
                          //    Vm.debug("nombre de la tarjeta:"+contenido);
                          }else if(nombre_campo.compareTo("Tipo")==0){
                              Tarj.tipo = contenido;
                           //   Vm.debug("tipo de la tarjeta: "+contenido);
                          }
                      break;
                  default:
                      break;
                      
              }
          }
          
		}catch(Exception exception){
			Log.v("XML","procesamiento de:"+contenido);
		}
	}
	
	/**
	 * Here we save the result of parse in SQLite Database. 
	 */
	public void endDocument(){
		//transform the results to WNE database.
		
		MjeBundle bundle = new MjeBundle();
		bundle.setAdmiteList(listaAdmite);
		bundle.setDireccionList(listaDireccion);
		bundle.setRestauranteList(listaRestaurante);
		bundle.setTarjetasList(listaTarjetas);
		bundle.setTipoCocinaList(listaTipoCocina);
		
		Mje2wneOntologyImpl ontologyImpl = new Mje2wneOntologyImpl();
		try{
		motorConsultas.persistPlaces(ontologyImpl.transform(bundle));
		}catch(Exception exception){
			if(exception!=null){
				exception.printStackTrace();
			//Log.d("Exception",exception.getLocalizedMessage());
			}
		}
		
		Log.v("XML",Integer.toString(listaRestaurante.size()));
		
		
		
		
		
		
	}
	public void endPrefixMapping(String prefix){
		
	}
	public void ignorableWhitespace(char[] ch, int start, int length){
		
	}
	public void setDocumentLocator(Locator locator){
		
	}
	public void skippedEntity(String name){
		
	}
	public void startElement(String uri, String localName, String qName, Attributes atts){
	//	Log.v("XML","URI: "+uri+" localname: "+localName+" qName: "+qName);
		String nombre_tabla = localName;
		if(nombre_tabla.compareTo("admite")==0){
            //   Vm.debug("encontrada tabla admite!!");
              tabla = 0;
              dentro_tabla = true;
              nombre_campo= new String("");
          }else if(nombre_tabla.compareTo("restaurante")==0){
              tabla = 1;
              dentro_tabla = true;
              nombre_campo= new String("");
          }else if(nombre_tabla.compareTo("direccion")==0){
              tabla = 2;
              dentro_tabla = true;
              nombre_campo= new String("");
          }else if(nombre_tabla.compareTo("tipoCocina")==0){
              tabla = 3;
              dentro_tabla = true;
              nombre_campo= new String("");
          }else if(nombre_tabla.compareTo("tarjetas")==0){
              tabla = 4;
              dentro_tabla = true;
              nombre_campo= new String("");
          }else if(dentro_tabla){
              nombre_campo = new String(qName);
          }
	}
	public void startPrefixMapping(String prefix, String uri){
		
	}
	public void endElement(String uri, String localName, String qName){
		//Vm.debug("EndTagName: " +  new String(buffer, offset, count));
        //una vez que he leido una etiqueta de fin:
        //primero compruebo si se trata de una etiqueta de fin de tabla o de fin de atributo:
	//	Log.v("XML","end Element");
        String nombre_tabla =localName;
        if(nombre_tabla.compareTo("admite")==0){
          //  Vm.debug("Encontrada fin de tabla admite!!");
            tabla = -1;
            dentro_tabla = false;
            //copio el objeto admite que he creado en el vector de objetos.
            
            listaAdmite.add(new admite(Admite.getNombreT(),Admite.getNombreRes()));
            //reseteo el objeto admite
            Admite.reset();
        }else if(nombre_tabla.compareTo("restaurante")==0){
            tabla = -1;
            dentro_tabla = false;
            //copio el objeto restaurante en el vector de objetos
            listaRestaurante.add(new restaurante(Res.nombre,Res.descripcion,null,Res.pMin,Res.pMax,
                          Res.pAvg,Res.tCocina,Res.fum,Res.Him,Res.Hfm,Res.Hit,Res.Hft,Res.Dd,
                          Res.menu,Res.soles,Res.Acc,Res.Sp,Res.Ap,Res.ApPort,Res.tj,Res.aic,
                          Res.bd, Res.lp,Res.ni,Res.tarjs,Res.direccion,Res.tipoCocina));
            //reseteo el objeto restaurante:
            Res.reset();
        }else if(nombre_tabla.compareTo("direccion")==0){
            tabla = -1;
            dentro_tabla = false;
            //copio el objeto direccion dentro del vector de objetos:
            listaDireccion.add(new direccion(Dir.idDireccion,Dir.calle,Dir.numero,Dir.ciudad,
                      Dir.provincia,Dir.Telefono,Dir.codP,Dir.coordX,Dir.coordY,Dir.zonaH));
            Dir.reset();
        }else if(nombre_tabla.compareTo("tipoCocina")==0){
            tabla = -1;
            dentro_tabla = false;
            listaTipoCocina.add(new tipoCocina(TiCo.nombre,TiCo.tipo));
           // Vm.debug("Nueva tipo cocina :"+TiCo.nombre+" "+TiCo.tipo);
            TiCo.reset();
        }else if(nombre_tabla.compareTo("tarjetas")==0){
            tabla = -1;
            dentro_tabla = false;
            listaTarjetas.add(new tarjetas(Tarj.nombre,Tarj.tipo));
            //Vm.debug("Nueva tarjeta :"+Tarj.nombre+" tipo: "+Tarj.tipo);
           // Vm.debug("tamano del vector:"+tarjs.size());
            Tarj.reset();
            
        }else if(dentro_tabla){
            nombre_campo = "";
        }
        
	}
}
